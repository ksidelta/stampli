import 'jsdom-global/register';

import { configure, mount } from 'enzyme';
import React from 'react';
import { InjectionContext } from '../../context/InjectionContext';
import { TokenService } from '../../../../services/token/TokenService';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { AuthenticatedCondition } from './AuthenticatedCondition';
import { BasicTokenService } from '../../../../services/token/BasicTokenService';
import { makeAutoObservable } from 'mobx';

configure({ adapter: new Adapter() });

describe('AuthenticatedCondition tests', () => {
  it('when user is logged then AuthenticatedCondition renders', () => {
    const tokenService: TokenService = {
      isAuthenticated(): boolean {
        return true;
      }
    } as TokenService;
    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <AuthenticatedCondition>
          <div className={'renders'} />
        </AuthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeTruthy();
  });

  it('when user is not logged then AuthenticatedCondition renders not', () => {
    const tokenService: TokenService = {
      isAuthenticated(): boolean {
        return false;
      }
    } as TokenService;
    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <AuthenticatedCondition>
          <div className={'renders'} />
        </AuthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeFalsy();
  });

  it('when user logs then renders appears', () => {
    // given
    const tokenService: TokenService = makeAutoObservable(new BasicTokenService());
    tokenService.unsetToken();

    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <AuthenticatedCondition>
          <div className={'renders'} />
        </AuthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeFalsy();

    // when
    tokenService.setToken('SHIT');

    // then
    condition.update();
    expect(condition.exists('.renders')).toBeTruthy();
  });
});
