import { givwhenify } from '../../../src/utils/tests/GivWhenify';
import express from 'express';
import { BasicRequestService } from '../../../src/services/request/BasicRequestService';
import * as path from 'path';

describe('BasicRequestService', () => {
  givwhenify(
    'given mock server',
    () => {
      return new Promise<express.Express & { close: () => Promise<any> }>(resolve => {
        // @ts-ignore
        const server: express.Express & { close: () => Promise<any> } = express();
        server.get('/string', (req, res) => res.header('content-type', 'plain/text').send('string'));
        server.get('/image', (req, res) =>
          res
            //.header('content-type', 'image/png')
            .sendFile(path.join(process.cwd(), '/test/services/request/512x512.png'), console.error)
        );
        server.get('/json', (req, res) => res.send({ test: 'string' }));

        const http = server.listen(8099, () => {
          server.close = () => new Promise(res => http.close(res));
          resolve(server);
        });
      });
    },
    (server: () => express.Express) => {
      // @ts-ignore
      afterEach(() => server().close());

      givwhenify(
        'given BasicRequestService',
        async () => {
          return new BasicRequestService({ baseUrl: 'http://localhost:8099' });
        },
        requestService => {
          describe('different content types', () => {
            // xit('given imag9e endpont, when image is requested, then returned type is arraybuffer', () => {
            //   server().get('/image');
            // });

            it('when text is requested, then returned type is string', async () => {
              return requestService()
                .query('/string', 'get')

                .then(response => expect(response.payload).toEqual('string'));
            });

            it('when image is requested, then returned type is arraybuffer', async () => {
              return requestService()
                .query('/image', 'get', { accept: 'image/png' })

                .then(response => expect(response.payload).toBeInstanceOf(Buffer));
            });

            it('when json is requested, then returned type is json', async () => {
              return requestService()
                .query('/json', 'get')

                .then(response => expect(response.payload).toEqual({ test: 'string' }));
            });
          });
        }
      );
    }
  );
});
