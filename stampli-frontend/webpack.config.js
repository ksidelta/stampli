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
    fallback: {
      buffer: require.resolve('buffer'),
      crypto: require.resolve('crypto-browserify'),
      util: require.resolve('util'),
      stream: require.resolve('stream-browserify')
    },
    extensions: ['.tsx', '.ts', '.js', '.jsx']
  },
  devServer: {
    static: {
      directory: path.join(__dirname, 'public'),
    },
    hot: true,
    allowedHosts: ['all'],
    host: '0.0.0.0',
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
      },
    ]
  },
  plugins: [
    new webpack.EnvironmentPlugin({
      BASE_URL: 'https://stampli.at.hsp.sh',
      OAUTH__GOOGLE__CLIENT_ID: '155167860801-q5hj0hi3hhdrgton1aoplfd026tgfch7.apps.googleusercontent.com'
    }),
    new webpack.ProvidePlugin({
      process: 'process/browser',
    }),
    new HtmlWebpackPlugin({ template: path.join(__dirname, '/src/index.html') })
  ]
};
