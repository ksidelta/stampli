import React from 'react';
import { MobilePage } from './base/MobilePage';
import { Content } from '../components/simple/container/layout/structure/Content';
import { Header } from '../components/simple/Header';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import QrReader from 'react-qr-reader';

export const QRScanPage = () => (
  <MobilePage>
    <Header title={'Zeskanuj Pieczątke'} icon={faCircle} />
    <Content>
      <QrReader onScan={x => {
        if (x) window.location.href = x;
      }} onError={console.error} />
    </Content>
  </MobilePage>
);
