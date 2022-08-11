import 'jsdom-global/register';

import { configure, mount } from 'enzyme';
import React from 'react';
import { InjectionContext } from '../../context/InjectionContext';
import { TokenService } from '../../../../services/token/TokenService';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import Adapter from '@wojtekmaj/enzyme-adapter-react-17';
import { BasicTokenService } from '../../../../services/token/BasicTokenService';
import { makeAutoObservable } from 'mobx';
import { UnauthenticatedCondition } from './UnauthenticatedCondition';

configure({ adapter: new Adapter() });

describe('UnauthenticatedCondition tests', () => {
  it('when user is logged then AuthenticatedCondition renders', () => {
    const tokenService: TokenService = {
      isAuthenticated(): boolean {
        return true;
      }
    } as TokenService;
    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <UnauthenticatedCondition>
          <div className={'renders'} />
        </UnauthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeFalsy();
  });

  it('when user is not logged then AuthenticatedCondition renders not', () => {
    const tokenService: TokenService = {
      isAuthenticated(): boolean {
        return false;
      }
    } as TokenService;
    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <UnauthenticatedCondition>
          <div className={'renders'} />
        </UnauthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeTruthy();
  });

  it('when user logs then renders appears', () => {
    // given
    const tokenService: TokenService = makeAutoObservable(new BasicTokenService());
    tokenService.setToken('CRINGE');

    const condition = mount(
      <InjectionContext.Provider value={{ tokenService } as ServicesBundle}>
        <UnauthenticatedCondition>
          <div className={'renders'} />
        </UnauthenticatedCondition>
      </InjectionContext.Provider>
    );
    expect(condition.exists('.renders')).toBeFalsy();

    // when
    tokenService.unsetToken();

    // then
    condition.update();
    expect(condition.exists('.renders')).toBeTruthy();
  });
});
