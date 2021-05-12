import React, { useContext, useState } from 'react';
import { BusinessSettingsForm } from '../../../complex/business/settings/BusinessSettingsForm';
import { InjectionContext } from '../../context/InjectionContext';
import { ServicesBundle } from '../../../../services/ServicesBundle';
import { Observer } from 'mobx-react';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/events/form/input/InputEvent';
import { InputChangedEventToStateUpdate } from '../../../../events/consumers/form/input/InputChangedEventToStateUpdate';
import { InputState } from '../../../../state/form/input/InputState';
import { TitledTextInput } from '../../../simple/form/input/TitledTextInput';
import { InputChangedEventToApiQuery } from '../../../../events/consumers/form/input/InputChangedEventToApiQuery';

export const StatedBusinessSettingsForm = () => {
  const servicesBundle = useContext(InjectionContext);

  const [inputState] = useState(InputState.createStringInputState());

  const [nameTopic] = useState(() => {
    const nameSubject = new Subject<InputEvent<string>>();

    nameSubject.subscribe(new InputChangedEventToStateUpdate(inputState, 'name'));
    nameSubject.subscribe(
      new InputChangedEventToApiQuery(inputState, servicesBundle.requestService, 'name', 'http://example.com')
    );

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
