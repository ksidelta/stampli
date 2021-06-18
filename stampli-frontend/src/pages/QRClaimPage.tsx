import { MobilePage } from './base/MobilePage';
import { Header } from '../components/simple/Header';
import { faCircle } from '@fortawesome/free-solid-svg-icons/faCircle';
import { Content } from '../components/simple/container/layout/structure/Content';
import React, { useContext, useEffect, useState } from 'react';
import { InjectionContext } from '../components/stated/context/InjectionContext';
import { useParams } from 'react-router-dom';
import { endpointMap } from '../services/request/constants/EndpointMap';
import { BusinessStampsStated } from '../components/complex/business/stamps/BusinessStampsStated';
import { Center } from '../components/simple/container/layout/align/Center';

export const QRClaimPage = () => {
  const servicesBundle = useContext(InjectionContext);

  const { businessId, issuerId, nonce } = useParams<{ businessId: string; issuerId: string; nonce: string }>();

  const [qrClaimed, claim] = useState(false);

  useEffect(() => {
    servicesBundle.requestService
      .query(endpointMap.CLAIM_BUSINESS_QR_CHALLENGE(parseInt(businessId), parseInt(issuerId)), 'post', {}, { nonce })
      .then(x => console.log(x.payload))
      .then(() => claim(true));
  }, []);

  return (
    <MobilePage>
      <Header title={'Twoje pieczątki'} icon={faCircle} />
      <Content>
        <Center>{qrClaimed &&
        <BusinessStampsStated businessId={parseInt(businessId)} onClick={window.close} />}</Center>
      </Content>
    </MobilePage>
  );
};
