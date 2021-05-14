import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import React from 'react';
import { Content } from '../components/simple/container/layout/structure/Content';
import { StatedBusinessSettingsForm } from '../components/stated/business/settings/StatedBusinessSettingsForm';
import { StatedBusinessPreCreation } from '../components/stated/business/StatedBusinessPreCreation';

export const BusinessSettingsPage = () => (
  <MobilePage>
    <Header title={'Ustawienia Twojego starego'} />
    <Content>
      <StatedBusinessPreCreation>
        <StatedBusinessSettingsForm />
      </StatedBusinessPreCreation>
    </Content>
  </MobilePage>
);
