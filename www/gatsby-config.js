const remark = require('remark')
const visit = require('unist-util-visit')

function isString (str) {
  return typeof str === 'string'
}

function isNumber (num) {
  return typeof num == 'number'
}

function isArray (arr) {
  return Array.isArray(arr)
}

module.exports = {
  siteMetadata: {
    title: `Spellsource`,
  },
  plugins: [
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        name: `src`,
        path: `${__dirname}/src/`,
      },
    },
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        name: `src`,
        path: `${__dirname}/../unityclient/Assets/UBlockly/JsonBlocks/`,
      },
    }, {
      resolve: `gatsby-source-filesystem`,
      options: {
        name: `src`,
        path: `${__dirname}/../unityclient/Assets/UBlockly/Toolboxes/Configs`,
      },
    },
    {
      resolve: `gatsby-transformer-json-hooks`,
      options: {
        onTransformObject: ({ fileNode, object }) => {
          // this is a card JSON
          if (object.hasOwnProperty('fileFormatVersion')) {
            if (!object.id) {
              // Set the id
              object.id = fileNode.base.replace(/.json$/, '')
            }
            // Also set a path on the cards node which corresponds to its URL in the website
            object.path = '/cards/' + object.id
          } else if ((object.hasOwnProperty('args0') || object.hasOwnProperty('message0')) && object.hasOwnProperty('type')) {
            // this is a blockly block
            if (!object.id && !!object.type) {
              object.id = object.type
            }

            const newArgs = []
            // Patch up types
            for (let i = 0; i <= 9; i++) {
              if (!!object['args' + i.toString()]) {
                const args = object['args' + i.toString()]
                args.forEach(arg => {
                  if (arg.hasOwnProperty('value')) {
                    if (isNumber(arg.value)) {
                      arg['valueI'] = arg.value
                    } else if (isString(arg.value)) {
                      arg['valueS'] = arg.value
                    } else if (arg.value === true) {
                      arg['valueB'] = true
                    } else if (arg.value === false) {
                      arg['valueB'] = false
                    }
                    delete arg.value
                  }
                  if (!!arg.check) {
                    if (isArray(arg.check)) {
                      return
                    }

                    arg['check'] = [arg.check]
                  }
                })
                newArgs.push({ i: i, args: args })
                delete object['args' + i.toString()]
              } else {
                break
              }
            }
            const newMessages = []
            for (let i = 0; i <= 9; i++) {
              if (!!object['message' + i.toString()]) {
                newMessages.push(object['message' + i.toString()])
              } else {
                break
              }
              delete object['message' + i.toString()]
            }
            object.args = newArgs
            object.messages = newMessages
            object.path = '/blocks/' + object.id
          } else if (object.hasOwnProperty('Style') && object.hasOwnProperty('BlockCategoryList')) {
            // this is a toolbox definition
            if (!object.id && !!object.Style) {
              object.id = object.Style
            }
            object.path = '/toolboxes/' + object.id
          }
        },
        typeName: ({ node, object, isArray }) => {
          // This is card JSON
          if (object.hasOwnProperty('fileFormatVersion')) {
            return 'Card'
          } else if ((object.hasOwnProperty('args') || object.hasOwnProperty('messages')) && object.hasOwnProperty('type')) {
            // this is a blockly block
            return 'Block'
          } else if (object.hasOwnProperty('Style') && object.hasOwnProperty('BlockCategoryList')) {
            // this is a toolbox definition
            return 'Toolbox'
          }
          // return 'Json'
        }
      }
    },
    {
      resolve: `gatsby-source-filesystem`,
      options: {
        path: `${__dirname}/../cards/src/main/resources/cards`,
      },
    },
    `gatsby-plugin-sass`,
    `gatsby-plugin-sharp`,
    `gatsby-image`,
    `gatsby-transformer-sharp`,
    `gatsby-plugin-anchor-links`,
    {
      resolve: `gatsby-transformer-remark`,
      options: {
        plugins: [
          `gatsby-remark-copy-linked-files`,
          {
            resolve: `gatsby-remark-images`,
            options: {
              // It's important to specify the maxWidth (in pixels) of
              // the content container as this plugin uses this as the
              // base for generating different widths of each image.
              maxWidth: 700,
              linkImagesToOriginal: false,
              backgroundColor: 'transparent',
              withWebp: true,
              disableBgImage: true,
              quality: 100,
              wrapperStyle: `float: right; width: 100%; margin-left: 0.5em; margin-bottom: 0.5em;`
            },
          },
          {
            resolve: `gatsby-remark-autolink-headers`,
            options: {
              maintainCase: false,
              removeAccents: true,
            },
          },
        ],
      },
    },
    {
      resolve: `gatsby-plugin-s3`,
      options: {
        bucketName: 'www.playspellsource.com',
        protocol: 'https',
        hostname: 'www.playspellsource.com',
      },
    },
    {
      resolve: `@gatsby-contrib/gatsby-plugin-elasticlunr-search`,
      options: {
        // Fields to index
        fields: [`title`, `rawMarkdownBody`],
        // How to resolve each field`s value for a supported node type
        resolvers: {
          // For any node of type MarkdownRemark, list how to resolve the fields` values
          MarkdownRemark: {
            title: node => node.frontmatter.title,
            path: node => node.frontmatter.path,
            rawMarkdownBody: node => node.rawMarkdownBody,
            excerpt: node => {
              const excerptLength = 250 // Hard coded excerpt length
              let excerpt = ''
              const tree = remark().parse(node.rawMarkdownBody)
              visit(tree, 'text', (node) => {
                excerpt += node.value
              })
              return excerpt.slice(0, excerptLength) + '...'
            },
            nodeType: node => 'MarkdownRemark'
          },
          Card: {
            // TODO: Change the name of the field to be its content
            title: node => node.name,
            rawMarkdownBody: node => node.description,
            path: node => node.path,
            collectible: node => node.collectible,
            excerpt: node => node.description,
            nodeType: node => 'Card',
            heroClass: node => node.heroClass,
            baseManaCost: node => node.baseManaCost
          },
          Block: {
            title: node => node.type.replace('_', ' '),
            nodeType: node => 'Block',
            rawMarkdownBody: node => node.searchMessage
          }
        },
        // Optional filter to limit indexed nodes
        filter: (node, getNode) => true
      },
    },
  ],
}
