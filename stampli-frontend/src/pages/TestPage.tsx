import React from 'react';
import { MobilePage } from './base/MobilePage';
import { VerticalFlex } from '../components/concrete/container/flex/VerticalFlex';

export const TestPage = () => (
  <MobilePage>
    <VerticalFlex>
      <div style={{ flexGrow: 1 }} />
      <div style={{ textAlign: 'center' }}>JEBAĆ TEDEGO!</div>
      <div style={{ flexGrow: 1 }} />
    </VerticalFlex>
  </MobilePage>
);
