import React, { useContext, useState } from 'react';
import { BusinessSettingsForm } from '../../../complex/business/settings/BusinessSettingsForm';
import { InjectionContext } from '../../context/InjectionContext';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import { Observer } from 'mobx-react';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import { InputChangedEventToStateUpdate } from '../../../../events/producers/input/InputChangedEventToStateUpdate';
import { InputState } from '../../../../state/form/input/InputState';
import { TitledTextInput } from '../../../simple/form/input/TitledTextInput';
import { InputChangedEventToApiQuery } from '../../../../events/producers/input/InputChangedEventToApiQuery';
import { endpointMap } from '../../../../services/request/constants/EndpointMap';

export const StatedBusinessSettingsForm = ({ businessId }: { businessId: number }) => {
  const servicesBundle = useContext(InjectionContext);

  const [inputState] = useState(InputState.createStringInputState());

  const [nameTopic] = useState(() => {
    const nameSubject = new Subject<InputEvent<String>>();

    nameSubject.subscribe(new InputChangedEventToStateUpdate(inputState, 'name'));
    nameSubject.subscribe(
      new InputChangedEventToApiQuery(
        inputState,
        servicesBundle.requestService,
        'name',
        endpointMap.BUSINESS_NAME(businessId)
      )
    );
    servicesBundle.eventRequester
      .onSubject(nameSubject)
      .withPayloadType(String)
      .request(endpointMap.BUSINESS_NAME(businessId));

    return nameSubject;
  });

  return (
    <InjectionContext.Consumer>
      {({ businessSettings }: ServicesBundle) => (
        <Observer>
          {() => (
            <>
              <TitledTextInput subject={nameTopic} state={inputState} title={'test'} />
              <BusinessSettingsForm
                businessName={businessSettings.name || ''}
                businessNameOnChange={name => businessSettings.updateName(name)}
                imageUrl={businessSettings.imageUrl}
                imageOnChange={(file, url) => businessSettings.updateImage(file, url)}
              />
            </>
          )}
        </Observer>
      )}
    </InjectionContext.Consumer>
  );
};
