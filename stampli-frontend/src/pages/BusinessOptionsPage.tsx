import React from 'react';
import { Header } from '../components/concrete/Header';
import { MobilePage } from './base/MobilePage';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import { IconButton } from '../components/concrete/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';

export const BusinessOptionsPage = () => (
  <MobilePage>
    <Header title={'Wybierz akcje dla biznesu'} />
    <VerticalFlex>
      <IconButton text={'Przenieś mnie do rozdawania pieczątek'} icon={faArrowRight} />
      <IconButton text={'Skonfiguruj dane biznesu'} icon={faArrowRight} />
    </VerticalFlex>
  </MobilePage>
);
