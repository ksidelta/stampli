import { ContextForm } from '../../contexted/form/ContextForm';
import { Delimeter } from '../../simple/container/flex/Delimeter';
import { ContextSubmitButton } from '../../contexted/form/input/ContextSubmitButton';
import React from 'react';
import { OpenIdConfiguration } from '../../../services/config/parts/OpenIdConfiguration';
import { GoogleLoginButton } from '../../simple/buttons/login/GoogleLoginButton';
import { LoginService } from '../../../services/login/LoginService';
import { RegisterService } from '../../../services/register/RegisterService';
import { ContextTitledInputComponent } from '../../contexted/form/input/ContextTitledInput';

export const BusinessLogin = ({
  loginService,
  registerService,
  openIdConfiguration,
  onSuccess
}: {
  loginService: LoginService;
  registerService: RegisterService;
  openIdConfiguration: OpenIdConfiguration;
  onSuccess: (token: string) => void;
}) => (
  <ContextForm
    definitions={[{ name: 'user', initialValue: 'twoj stary' }, { name: 'password' }]}
    onSubmit={x => {
      x.submit(x.user, x.password); // it comes from button values
    }}>
    <ContextTitledInputComponent title={'login'} name={'user'} />
    <ContextTitledInputComponent type={'password'} title={'hasło'} name={'password'} />
    <Delimeter />
    <ContextSubmitButton
      value={(username: string, password: string) => loginService.login(username, password)}
      text={'Zaloguj się'}
    />
    <ContextSubmitButton
      value={(username: string, password: string) =>
        registerService.register(username, password).then(() => loginService.login(username, password))
      }
      text={'Zarejestruj się'}
    />
    <GoogleLoginButton config={openIdConfiguration} onSuccess={onSuccess} />
  </ContextForm>
);
