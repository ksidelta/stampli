import { givwhenify } from '../../utils/tests/GivWhenify';
import { BasicRequestResponse } from './response/BasicRequestResponse';
import { BasicLoginService } from '../login/BasicLoginService';
import { AuthenticationAwareRequestService } from './AuthenticationAwareRequestService';

describe('AuthenticationAwareRequestService', () => {
  givwhenify(
    'given unlogged user',
    async () => {
      const service = new BasicLoginService();
      return service;
    },
    loginService => {
      it('when query is executed then authorization header is not set', async () => {
        const fun = jest.fn(async () => new BasicRequestResponse(200, {}));
        //@ts-ignore
        const service = new AuthenticationAwareRequestService({ query: fun }, loginService());
        await service.query('', 'get', {});

        expect(fun).toBeCalledWith('', 'get', {}, undefined);
      });

      it('when query returns Set-Token then loginService is updated', async () => {
        const fun = jest.fn(async () => new BasicRequestResponse(200, { 'Set-Token': 'SMTH' }));
        //@ts-ignore
        const service = new AuthenticationAwareRequestService({ query: fun }, loginService());
        await service.query('', 'get', {});

        expect(loginService().getToken()).toEqual('SMTH');
      });
    }
  );
  givwhenify(
    'given logged user',
    async () => {
      const service = new BasicLoginService();
      await service.setToken('lol');
      return service;
    },
    loginService => {
      it('when query is executed then authorization header is set', async () => {
        const fun = jest.fn(async () => new BasicRequestResponse(200, {}));
        //@ts-ignore
        const service = new AuthenticationAwareRequestService({ query: fun }, loginService());
        await service.query('', 'get', {});

        expect(fun).toBeCalledWith('', 'get', { Authorization: 'Bearer lol' }, undefined);
      });

      it('when Set-Token is returned then authorization header is updated', async () => {
        const fun = jest.fn(async () => new BasicRequestResponse(200, { 'Set-Token': 'SMTH' }));
        //@ts-ignore
        const service = new AuthenticationAwareRequestService({ query: fun }, loginService());
        await service.query('', 'get', {});

        expect(loginService().getToken()).toEqual('SMTH');
      });
    }
  );
});
