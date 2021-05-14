import React, { ReactElement, useContext, useState } from 'react';
import { InjectionContext } from '../context/InjectionContext';
import { Subject } from 'rxjs';
import { RequestResolvedEvent } from '../../../events/producers/request/RequestResolvedEvent';
import { RequestResponseState } from '../../../events/producers/request/RequestResponseState';
import { RequestResponseStateUpdater } from '../../../events/producers/request/RequestResponseStateUpdater';
import { endpointMap } from '../../../services/request/constants/EndpointMap';
import { observer } from 'mobx-react';
import { CenterMiddle } from '../../simple/container/layout/CenterMiddle';
import { IconButton } from '../../simple/buttons/IconButton';
import { faPlusCircle } from '@fortawesome/free-solid-svg-icons/faPlusCircle';

export const StatedBusinessPreCreation = observer(({ children }: { children: ReactElement }) => {
  const servicesBundle = useContext(InjectionContext);

  const [businessSubject] = useState(() => {
    const subject = new Subject<RequestResolvedEvent<any>>();
    return subject;
  });

  const loadBusiness = () =>
    servicesBundle.eventRequester.onSubject(businessSubject).withPayloadType(BusinessDto).request(endpointMap.BUSINESS);

  const [businessState] = useState(() => {
    const state = RequestResponseState.create();
    businessSubject.subscribe(new RequestResponseStateUpdater(state));
    loadBusiness();
    return state;
  });

  if (businessState.isRequestInProgress) {
    return <div>'LOADING KURWA'</div>;
  }

  if (businessState.response?.isSuccessful()) {
    return children;
  }

  return (
    <CenterMiddle>
      <IconButton
        text={'Stwórz Biznes'}
        onClick={() => {
          servicesBundle.requestService.query(endpointMap.BUSINESS, 'post').then(x => loadBusiness());
        }}
        icon={faPlusCircle}
      />
    </CenterMiddle>
  );
});

class BusinessDto {
  name!: string;
  id!: number;
}
