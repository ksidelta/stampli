import { givwhenify } from '../../utils/tests/GivWhenify';
import express from 'express';
import { BasicRequestService } from './BasicRequestService';

describe('BasicRequestService', () => {
  givwhenify(
    'given mock server',
    () => {
      return new Promise<express.Express>(resolve => {
        const server: express.Express = express();
        server.listen(() => resolve(server));
      });
    },
    (server: () => express.Express) => {
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

            it('given text endpoint, when text is requested, then returned type is string', async () => {
              server().get('/string', (req, res) => res.header('content-type', 'plain/text').send('string'));

              const response = await requestService().query('/string', 'get');

              await expect(response).resolves.toEqual('string');
            });
          });
        }
      );
    }
  );
});
