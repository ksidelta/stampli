import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import React, { useContext } from 'react';
import { Content } from '../components/simple/container/layout/structure/Content';
import { StatedBusinessSettingsForm } from '../components/stated/business/settings/StatedBusinessSettingsForm';
import { StatedBusinessPreCreation } from '../components/stated/business/StatedBusinessPreCreation';
import { faCheck } from '@fortawesome/free-solid-svg-icons/faCheck';
import { InjectionContext } from '../components/stated/context/InjectionContext';

export const BusinessSettingsPage = () => {
  const servicesBundle = useContext(InjectionContext);
  const routing = servicesBundle.routingService;

  return (
    <MobilePage>
      <Header title={'Ustawienia Biznesu'} icon={faCheck} onClick={() => routing.goToBusinessOptionsPage()} />
      <Content>
        <StatedBusinessPreCreation>{id => <StatedBusinessSettingsForm businessId={id} />}</StatedBusinessPreCreation>
      </Content>
    </MobilePage>
  );
};
