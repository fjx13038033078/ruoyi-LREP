'use strict'
const path = require('path')

function resolve(dir) {
  return path.join(__dirname, dir)
}

const port = process.env.port || process.env.npm_config_port || 80 // 端口

module.exports = {
  publicPath: process.env.NODE_ENV === "production" ? "/" : "/",
  outputDir: 'dist',
  assetsDir: 'static',
  lintOnSave: process.env.NODE_ENV === 'development',
  productionSourceMap: false,
  devServer: {
    host: '0.0.0.0',
    port: port,
    open: true,
    proxy: {
      // 配置跨域
      ['/api']: {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          ['^/api']: ''
        }
      }
    },
    disableHostCheck: true
  },
  configureWebpack: {
    name: '学习资源分享平台',
    resolve: {
      alias: {
        '@': resolve('src')
      }
    }
  }
} 