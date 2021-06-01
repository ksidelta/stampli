import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { Content } from '../components/simple/container/layout/structure/Content';
import React, { useContext } from 'react';
import { CenterMiddle } from '../components/simple/container/layout/CenterMiddle';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { useParams } from 'react-router-dom';

export const QRClaimPage = () => {
  const servicesBundle = useContext(InjectionContext);

  const { businessId, issuerId, nonce } = useParams<{ businessId: string; issuerId: string; nonce: string }>();

  return (
    <MobilePage>
      <Header title={'Twoje pieczątki'} icon={faCircle} />
      <Content>
        <CenterMiddle>{`${businessId} ${issuerId} ${nonce}`}</CenterMiddle>
      </Content>
    </MobilePage>
  );
};
