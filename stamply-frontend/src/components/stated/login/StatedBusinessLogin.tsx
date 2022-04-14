import { BusinessLogin } from '../../complex/login/BusinessLogin';
import React from 'react';
import { InjectionContext } from '../context/InjectionContext';

export const StatedBusinessLogin = () => (
  <InjectionContext.Consumer>
    {({ config: { openIdConfiguration }, tokenService, loginService, registerService, externalTokenService }) => (
      <BusinessLogin
        loginService={loginService}
        openIdConfiguration={openIdConfiguration}
        onSuccess={(token) => {
          externalTokenService.regenerateToken(token);
        }}
        registerService={registerService}
      />
    )}
  </InjectionContext.Consumer>
);
