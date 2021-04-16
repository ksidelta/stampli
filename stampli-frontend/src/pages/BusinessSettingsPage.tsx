import { MobilePage } from './base/MobilePage';
import { Header } from '../components/concrete/Header';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import React from 'react';

export const BusinessOptionsPage = () => (
  <MobilePage>
    <Header title={'Wybierz rakcje dla biznesu'} />
    <VerticalFlex></VerticalFlex>
  </MobilePage>
);
