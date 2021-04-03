import { BusinessLogin } from '../../complex/login/BusinessLogin';
import React from 'react';
import { InjectionContext } from '../context/InjectionContext';

export const StatedBusinessLogin = (
  <InjectionContext.Consumer>
    {({ config: { openIdConfiguration }, loginService }) => (
      <BusinessLogin openIdConfiguration={openIdConfiguration} onSuccess={loginService.setToken} />
    )}
  </InjectionContext.Consumer>
);
