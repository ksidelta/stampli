import React from 'react';
import { TextInputState, TextInputStateImpl } from '../../simple/form/input/TextInputState';
import { TitledTextInput } from '../../simple/form/input/TextInput';
import { Delimeter } from '../../../../components/simple/container/flex/Delimeter';
import { CommonButton } from '../../simple/form/button/CommonButton';
import { LoginService } from '../../../../services/login/LoginService';
import { RegisterService } from '../../../../services/register/RegisterService';
import { OpenIdConfiguration } from '../../../../services/config/parts/OpenIdConfiguration';
import { GoogleLoginButton } from '../../../../components/simple/buttons/login/GoogleLoginButton';

interface Props {
  loginService: LoginService;
  registerService: RegisterService;
  openIdConfiguration: OpenIdConfiguration;
  onSuccess: (token: string) => void;
}

export class BusinessLogin extends React.Component<Props, {}> {
  usernameState: TextInputState = TextInputStateImpl.create('');
  passwordState: TextInputState = TextInputStateImpl.create('');

  constructor(public props: Props) {
    super(props);
  }

  render(): JSX.Element {
    return <>
      <TitledTextInput state={this.usernameState} title={'Login'} type={'text'} />
      <TitledTextInput state={this.passwordState} title={'Password'} type={'password'} />
      <Delimeter />
      <CommonButton text={'login'} onClick={() => {
        this.props.loginService.login(this.usernameState.text(), this.passwordState.text());
      }} />
      <CommonButton text={'register'} onClick={() => {
        this.props.registerService.register(this.usernameState.text(), this.passwordState.text());
      }} />
      <GoogleLoginButton config={this.props.openIdConfiguration} onSuccess={this.props.onSuccess} />
    </>;
  }
}