import express from 'express';

import webpack from 'webpack';
import WebpackMiddleware from 'webpack-dev-middleware';

import config from '../webpack.config';
import * as path from 'path';

import rewrite from 'express-urlrewrite';

export function createDevServer(): Promise<void> {
  return new Promise(async resolve => {
    const server = express();

    await configureRoutes(server);

    server.listen(8080, () => {
      resolve();
    });
  });
}

async function configureRoutes(server: express.Express) {
  await directToWebpackButWaitForCompilation(server);
  directToPublic(server);
  directToIndex(server);
}

async function directToWebpackButWaitForCompilation(server: express.Express): Promise<void> {
  return new Promise(resolve => {
    // eslint-disable-next-line @typescript-eslint/ban-ts-comment
    // @ts-ignore
    const compiler = webpack(config); // ignoring because config is not typed
    const webpackMiddleware = WebpackMiddleware(compiler, {
      writeToDisk: true
    });

    server.use(webpackMiddleware);

    webpackMiddleware.waitUntilValid(() => resolve());
  });
}

function directToPublic(server: express.Express) {
  server.use(express.static(getPublicFolder()));
}

function directToIndex(server: express.Express) {
  server.use(rewrite('.*', '/index.html'));
  server.use('*', express.static(getPublicFolder()));
}

function getPublicFolder() {
  return path.join(__dirname, '../public');
}
