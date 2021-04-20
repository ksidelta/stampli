import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { VerticalFlex } from '../components/simple/container/flex/VerticalFlex';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons/faArrowLeft';
import { StatedBusinessLogin } from '../components/stated/login/StatedBusinessLogin';
import { Content } from '../components/simple/container/layout/structure/Content';

export const BusinessLoginPage = () => (
  <MobilePage>
    <Header title={'Zaloguj się do konsoli biznesu'} icon={faArrowLeft} />
    <Content>
      <StatedBusinessLogin />
    </Content>
  </MobilePage>
);
