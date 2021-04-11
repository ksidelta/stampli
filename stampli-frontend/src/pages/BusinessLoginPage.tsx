import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { StatedBusinessLogin } from '../components/stated/login/StatedBusinessLogin';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faArrowLeft} />
    <VerticalFlex>
      <StatedBusinessLogin />
    </VerticalFlex>
  </MobilePage>
);
