import React, { useContext, useState } from 'react';
import { InjectionContext } from '../../../stated/context/InjectionContext';
import { BusinessStampsComponent } from './BusinessStampsComponent';

export const BusinessStampsStated = ({ businessId }: { businessId: number }) => {
  const servicesBundle = useContext(InjectionContext);

  const stampService = servicesBundle.stampService;
  const businessInfoService = servicesBundle.businessInfoService;

  const [state, updateState] = useState<[boolean, number, string]>([false, 0, '']);

  Promise.all([stampService.getStamps(businessId), businessInfoService.getLogoSrc(businessId)]).then(
    ([quantityOfStamps, logoSrc]) => {
      updateState([true, quantityOfStamps, logoSrc]);
    }
  );

  const [loaded, quantityOfStamps, logoSrc] = state;

  return loaded ? <BusinessStampsComponent stampsQuantity={quantityOfStamps} logoSrc={logoSrc} /> : null;
};
