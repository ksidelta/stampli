import 'jest-localstorage-mock';
import { givwhenify } from '../../utils/tests/GivWhenify';
import { LOCALSTORAGE_TOKEN, LocalStorageTokenService } from './LocalStorageTokenService';
import { BasicTokenService } from './BasicTokenService';

describe('test LocalStorageTokenService', () => {
  givwhenify(
    'given LocalStorageTokenService',
    async () => new LocalStorageTokenService(new BasicTokenService()),
    tokenService => {
      givwhenify(
        'given no token in local storage',
        async () => {},
        () => {
          givwhenify(
            'when token is got',
            async () => tokenService().getToken(),
            token => {
              it('then token is undefined', () => {
                expect(token()).toBeUndefined();
              });
            }
          );

          it('then storage is unauthenticated', () => {
            expect(tokenService().isAuthenticated()).toBeFalsy();
          });
        }
      );

      givwhenify(
        'given token in local storage',
        async () => localStorage.setItem(LOCALSTORAGE_TOKEN, 'smth'),
        () => {
          givwhenify(
            'when token is got',
            async () => tokenService().getToken(),
            token => {
              it('then token is smth', () => {
                expect(token()).toEqual('smth');
              });
            }
          );

          givwhenify(
            'when token is updated',
            async () => tokenService().setToken('other'),
            () => {
              it('then token is other', () => {
                expect(tokenService().getToken()).toEqual('other');
              });

              it('then token in new LocalStorageTokenService is other', () => {
                expect(new LocalStorageTokenService(new BasicTokenService()).getToken()).toEqual('other');
              });
            }
          );

          it('then storage is authenticated', () => {
            expect(tokenService().isAuthenticated()).toBeTruthy();
          });
        }
      );
    }
  );
});
