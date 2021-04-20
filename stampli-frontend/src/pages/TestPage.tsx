import React from 'react';
import { MobilePage } from './base/MobilePage';
import { Content } from '../components/simple/container/layout/structure/Content';

export const TestPage = () => (
  <MobilePage>
    <Content>
      <div style={{ flexGrow: 1 }} />
      <div style={{ textAlign: 'center' }}>JEBAĆ TEDEGO!</div>
      <div style={{ flexGrow: 1 }} />
    </Content>
  </MobilePage>
);
