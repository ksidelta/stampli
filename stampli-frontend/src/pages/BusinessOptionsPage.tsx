import React, { useContext } from 'react';
import { Header } from '../components/simple/Header';
import { MobilePage } from './base/MobilePage';
import { IconButton } from '../components/simple/buttons/IconButton';
import { faArrowRight } from '@fortawesome/free-solid-svg-icons/faArrowRight';
import { Content } from '../components/simple/container/layout/structure/Content';
import { faArrowLeft } from '@fortawesome/free-solid-svg-icons';
import { InjectionContext } from '../components/stated/context/InjectionContext';

export const BusinessOptionsPage = () => {
  const servicesBundle = useContext(InjectionContext);
  const routing = servicesBundle.routingService;

  return (
    <MobilePage>
      <Header title={'Wybierz akcje dla biznesu'} icon={faArrowLeft} onClick={() => routing.goToHome()} />
      <Content>
        <IconButton
          text={'Przenieś mnie do rozdawania pieczątek'}
          icon={faArrowRight}
          onClick={history => history.goToBusinessQRCode()}
        />
        <IconButton
          text={'Skonfiguruj dane biznesu!'}
          icon={faArrowRight}
          onClick={history => history.goToBusinessSettingsPage()}
        />
      </Content>
    </MobilePage>
  );
};
