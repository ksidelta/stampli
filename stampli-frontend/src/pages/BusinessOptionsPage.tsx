import React from 'react';
import { Header } from '../components/simple/Header';
import { MobilePage } from './base/MobilePage';
import { IconButton } from '../components/simple/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';
import { Content } from '../components/simple/container/layout/structure/Content';

export const BusinessOptionsPage = () => (
  <MobilePage>
    <Header title={'Wybierz akcje dla biznesu'} />
    <Content>
      <IconButton text={'Przenieś mnie do rozdawania pieczątek'} icon={faArrowRight} />
      <IconButton
        text={'Skonfiguruj dane biznesu!'}
        icon={faArrowRight}
        onClick={history => history.goToBusinessSettingsPage()}
      />
    </Content>
  </MobilePage>
);
