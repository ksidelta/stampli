// eslint-disable-next-line @typescript-eslint/no-var-requires
const path = require('path');
const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: './src/main.tsx',
  devtool: 'eval-source-map',
  mode: 'development',
  target: 'web',
  output: {
    path: path.join(__dirname, '/public/script'),
    filename: 'bundle.js',
    publicPath: '/'
  },
  resolve: {
    extensions: ['.tsx', '.ts', '.js', '.jsx']
  },
  devServer: {
    hot: true,
    historyApiFallback: {
      index: '/index.html'
    }
  },
  module: {
    rules: [
      {
        test: /\.[tj]sx?$/,
        exclude: path.resolve(__dirname, 'node_modules'),
        loader: 'babel-loader'
      },
      {
        test: /\.(css)$/,
        use: ['style-loader', 'css-loader']
      },
      {
        test: /\.svg$/,
        loader: 'svg-inline-loader'
      }
    ]
  },
  plugins: [
    new webpack.EnvironmentPlugin(['BASE_URL', 'OAUTH__GOOGLE__CLIENT_ID']),
    new HtmlWebpackPlugin({ template: 'src/index.html' })
  ]
};
