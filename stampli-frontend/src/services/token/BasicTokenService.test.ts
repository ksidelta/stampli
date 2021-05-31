import { givwhenify } from '../../utils/tests/GivWhenify';
import { BasicTokenService } from './BasicTokenService';

describe('BasicTokenService', () => {
  givwhenify(
    'given BasicTokenService',
    async () => new BasicTokenService(),
    service => {
      givwhenify(
        'given token is set',
        async () =>
          service().setToken(
            'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZXMiOlsiVVNFUiJdLCJpc3MiOiJodHRwczovL3N0YW1wbGkuYXQuaHNwLnNoLyIsImV4cCI6MTYyMjM3NDk4MSwiaWF0IjoxNjIxNzcwMTgxfQ.RQhyJf7yAeliw8sgKHn75esQxoORsyw3NPFI6qWGDKg'
          ),
        () => {
          it('when user id is got, then correct is returned', () => {
            expect(service().getUserId()).toEqual(1);
          });
        }
      );
    }
  );
});
