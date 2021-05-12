import React from 'react';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/events/form/input/InputEvent';
import { InputChangedEvent } from '../../../../events/events/form/input/InputChangedEvent';

export const EventDrivenTextInput = ({
  topic,
  children
}: {
  topic: Subject<InputEvent<string>>;
  children: (consumer: (value: string) => void) => React.ReactNode;
}) => <>{children(value => topic.next(new InputChangedEvent('name', value)))}</>;
