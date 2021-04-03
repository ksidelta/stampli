import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { BusinessLogin } from '../components/complex/login/BusinessLogin';
import { OpenIdConfiguration } from '../services/config/parts/OpenIdConfiguration';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faArrowLeft} />
    <VerticalFlex>
      <BusinessLogin openIdConfiguration={new OpenIdConfiguration('twojastara')} />
    </VerticalFlex>
  </MobilePage>
);
