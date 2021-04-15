import { BusinessLogin } from '../../complex/login/BusinessLogin';
import React from 'react';
import { InjectionContext } from '../context/InjectionContext';

export const StatedBusinessLogin = () => (
  <InjectionContext.Consumer>
    {({ config: { openIdConfiguration }, tokenService, loginService, registerService }) => (
      <BusinessLogin
        loginService={loginService}
        openIdConfiguration={openIdConfiguration}
        onSuccess={tokenService.setToken}
        registerService={registerService}
      />
    )}
  </InjectionContext.Consumer>
);
