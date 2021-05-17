import React, { useContext, useState } from 'react';
import { InjectionContext } from '../../context/InjectionContext';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import { Observer } from 'mobx-react';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import { InputState } from '../../../../state/form/input/InputState';
import { TitledTextInput } from '../../../simple/form/input/TitledTextInput';
import { InputChangedEventToTextApiQuery } from '../../../../events/producers/input/text/InputChangedEventToTextApiQuery';
import { endpointMap } from '../../../../services/request/constants/EndpointMap';
import { InputChangedEventToTextStateUpdate } from '../../../../events/producers/input/text/InputChangedEventToTextStateUpdate';
import { ImageUpload } from '../../../simple/form/img/ImageUpload';
import { ImageValue } from '../../../simple/form/img/ImageValue';
import { InputChangedEventToImageStateUpdate } from '../../../../events/producers/input/img/InputChangedEventToImageStateUpdate';
import { InputChangedEventToImageApiQuery } from '../../../../events/producers/input/img/InputChangedEventToImageApiQuery';

export const StatedBusinessSettingsForm = ({ businessId }: { businessId: number }) => {
  const servicesBundle = useContext(InjectionContext);

  const [inputState] = useState(InputState.createStringInputState());

  const [nameTopic] = useState(() => {
    const nameSubject = new Subject<InputEvent<String>>();

    nameSubject.subscribe(new InputChangedEventToTextStateUpdate(inputState));
    nameSubject.subscribe(
      new InputChangedEventToTextApiQuery(
        inputState,
        servicesBundle.requestService,
        endpointMap.BUSINESS_NAME(businessId)
      )
    );
    servicesBundle.eventRequester
      .onSubject(nameSubject)
      .withPayloadType(String)
      .request(endpointMap.BUSINESS_NAME(businessId));

    return nameSubject;
  });

  const [imageState] = useState(InputState.createImageState());

  const [imageTopic] = useState(() => {
    const imageSubject = new Subject<InputEvent<ImageValue>>();

    imageSubject.subscribe(new InputChangedEventToImageStateUpdate(imageState));
    imageSubject.subscribe(
      new InputChangedEventToImageApiQuery(
        imageState,
        servicesBundle.requestService,
        endpointMap.BUSINESS_LOGO(businessId)
      )
    );

    servicesBundle.eventRequester
      .onSubject(imageSubject)
      .withConversion((binary: Buffer) => {
        return new ImageValue(binary, URL.createObjectURL(binary));
      })
      .request(endpointMap.BUSINESS_LOGO(businessId));

    return imageSubject;
  });

  return (
    <InjectionContext.Consumer>
      {({ businessSettings }: ServicesBundle) => (
        <Observer>
          {() => (
            <>
              <TitledTextInput subject={nameTopic} state={inputState} title={'testu`'} />
              <ImageUpload topic={imageTopic} state={imageState} />
            </>
          )}
        </Observer>
      )}
    </InjectionContext.Consumer>
  );
};
