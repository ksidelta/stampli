import { MobilePage } from './base/MobilePage';
import { Header } from '../components/concrete/Header';
import { VerticalFlex } from '../components/simple/container/flex/VerticalFlex';
import React from 'react';
import { ImageUpload } from '../components/concrete/upload/img/ImageUpload';
import { BusinessSettingsForm } from '../components/complex/business/settings/BusinessSettingsForm';
import { Content } from '../components/simple/container/layout/structure/Content';

export const BusinessSettingsPage = () => (
  <MobilePage>
    <Header title={'Ustawienia Twojego starego'} />
    <Content>
      <BusinessSettingsForm />
    </Content>
  </MobilePage>
);
