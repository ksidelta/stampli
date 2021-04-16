import { MobilePage } from './base/MobilePage';
import { Header } from '../components/concrete/Header';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';
import { IconButton } from '../components/concrete/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';
import React from 'react';
import { ContextTitledInput } from '../components/contexted/form/input/ContextTitledInput';

export const BusinessOptionsPage = () => (
  <MobilePage>
    <Header title={'Wybierz akcje dla biznesu'} />
    <VerticalFlex></VerticalFlex>
  </MobilePage>
);
