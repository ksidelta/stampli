import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { ContextForm } from '../components/concrete/form/ContextForm';
import { ContextTitledInput } from '../components/concrete/form/input/ContextTitledInput';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import { ContextSubmitButton } from '../components/concrete/form/input/ContextSubmitButton';
import { Delimeter } from '../components/concrete/container/flex/Delimeter';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import GoogleLogin from 'react-google-login';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faArrowLeft} />
    <VerticalFlex>
      <ContextForm
        definitions={[{ name: 'user', initialValue: 'twoj stary' }, { name: 'password' }]}
        onSubmit={x => console.log(x)}>
        <ContextTitledInput title={'login'} name={'user'} />
        <ContextTitledInput title={'hasło'} name={'password'} />
        <Delimeter />
        <ContextSubmitButton text={'Zaloguj się'} />
        <ContextSubmitButton text={'Zarejestruj się'} />
        <GoogleLogin
          redirectUri={'/api/login/oauth2/code/google'}
          clientId="155167860801-d72fthptdqh1a4ssu59h87923lbk7jgi.apps.googleusercontent.com"
          buttonText="Login"
          onSuccess={x => console.log(x)}
          onFailure={x => console.error(x)}
          cookiePolicy={'single_host_origin'}
        />
      </ContextForm>
    </VerticalFlex>
  </MobilePage>
);
