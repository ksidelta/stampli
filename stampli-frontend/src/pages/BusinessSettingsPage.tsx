import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { VerticalFlex } from '../components/simple/container/flex/VerticalFlex';
import React from 'react';
import { ImageUpload } from '../components/simple/upload/img/ImageUpload';
import { BusinessSettingsForm } from '../components/complex/business/settings/BusinessSettingsForm';
import { Content } from '../components/simple/container/layout/structure/Content';
import { StatedBusinessSettingsForm } from '../components/stated/business/settings/StatedBusinessSettingsForm';

export const BusinessSettingsPage = () => (
  <MobilePage>
    <Header title={'Ustawienia Twojego starego'} />
    <Content>
      <StatedBusinessSettingsForm />
    </Content>
  </MobilePage>
);
