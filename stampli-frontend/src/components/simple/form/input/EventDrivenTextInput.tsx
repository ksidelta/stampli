import React from 'react';
import { Subject } from 'rxjs';
import { InputEvent } from '../../../../events/producers/input/InputEvent';
import { InputChangedEvent } from '../../../../events/producers/input/InputChangedEvent';

export const EventDrivenTextInput = ({
  topic,
  children
}: {
  topic: Subject<InputEvent<string>>;
  children: (consumer: (value: string) => void) => React.ReactNode;
}) => <>{children(value => topic.next(new InputChangedEvent('name', value)))}</>;
