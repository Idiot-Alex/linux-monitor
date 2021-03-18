module.exports = {
  // 代理设置
  devServer: {
    port: 3000,
    proxy: {
      '/*': {
        target: 'http://localhost:8080',
        timeout: 5 * 1000
      }
    }
  }
}