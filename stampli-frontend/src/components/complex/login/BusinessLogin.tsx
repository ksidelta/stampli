import { ContextForm } from '../../concrete/form/ContextForm';
import { ContextTitledInput } from '../../concrete/form/input/ContextTitledInput';
import { Delimeter } from '../../concrete/container/flex/Delimeter';
import { ContextSubmitButton } from '../../concrete/form/input/ContextSubmitButton';
import React from 'react';
import { OpenIdConfiguration } from '../../../services/config/parts/OpenIdConfiguration';
import { GoogleLoginButton } from '../../concrete/buttons/login/GoogleLoginButton';

export const BusinessLogin = ({
  openIdConfiguration,
  onSuccess
}: {
  openIdConfiguration: OpenIdConfiguration;
  onSuccess: (token: string) => void;
}) => (
  <ContextForm
    definitions={[{ name: 'user', initialValue: 'twoj stary' }, { name: 'password' }]}
    onSubmit={x => console.log(x)}>
    <ContextTitledInput title={'login'} name={'user'} />
    <ContextTitledInput title={'hasło'} name={'password'} />
    <Delimeter />
    <ContextSubmitButton text={'Zaloguj się'} />
    <ContextSubmitButton text={'Zarejestruj się'} />
    <GoogleLoginButton config={openIdConfiguration} onSuccess={onSuccess} />
  </ContextForm>
);
