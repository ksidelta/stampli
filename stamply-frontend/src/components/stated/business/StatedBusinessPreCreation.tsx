import React, { useContext, useState } from 'react';
import { InjectionContext } from '../context/InjectionContext';
import { Subject } from 'rxjs';
import { RequestResponseState } from '../../../events/producers/request/RequestResponseState';
import { RequestResponseStateUpdater } from '../../../events/producers/request/RequestResponseStateUpdater';
import { endpointMap } from '../../../services/request/constants/EndpointMap';
import { CenterMiddle } from '../../simple/container/layout/CenterMiddle';
import { IconButton } from '../../simple/buttons/IconButton';
import { observer } from 'mobx-react';
import { InputEvent } from '../../../events/producers/input/InputEvent';

export const StatedBusinessPreCreation = observer(({ children }: { children: (id: number) => React.ReactElement }) => {
  const servicesBundle = useContext(InjectionContext);

  const [businessSubject] = useState(() => {
    const subject = new Subject<InputEvent<BusinessDto>>();
    return subject;
  });

  const loadBusiness = () =>
    servicesBundle.eventRequester.onSubject(businessSubject).withPayloadType(BusinessDto).request(endpointMap.BUSINESS);

  const [businessState] = useState(() => {
    const state: RequestResponseState<BusinessDto> = RequestResponseState.create<BusinessDto>();
    businessSubject.subscribe(new RequestResponseStateUpdater(state));
    loadBusiness();
    return state;
  });

  if (businessState.isRequestInProgress) {
    return <div>{'LOADING'}</div>;
  }

  if (businessState.response?.isSuccessful()) {
    return children((businessState.response?.payload as BusinessDto).id);
  }

  return (
    <CenterMiddle>
      <IconButton
        text={'Stwórz Biznes'}
        onClick={() => {
          servicesBundle.requestService.query(endpointMap.BUSINESS, 'post').then(x => loadBusiness());
        }}
      />
    </CenterMiddle>
  );
});

class BusinessDto {
  name!: string;
  id!: number;
}
