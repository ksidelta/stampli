import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { ContextForm } from '../components/concrete/form/ContextForm';
import { ContextTitledInput } from '../components/concrete/form/input/ContextTitledInput';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faCircle} />
    <VerticalFlex>
      <ContextForm
        definitions={[{ name: 'user', initialValue: 'twoj stary' }, { name: 'password' }]}
        onSubmit={x => console.log(x)}>
        <ContextTitledInput title={'login'} name={'user'} />
        <ContextTitledInput title={'hasło'} name={'password'} />
      </ContextForm>
    </VerticalFlex>
  </MobilePage>
);
