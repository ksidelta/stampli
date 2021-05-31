import { InputState } from '../../../state/form/input/InputState';
import { StatedChallengeQRCode } from './StatedChallengeQRCode';
import { ChallengeQRCodeComponent } from './ChallengeQRCodeComponent';
import React from 'react';

export const ChallengeQRCode = ({ state }: { state: InputState<string> }) => (
  <StatedChallengeQRCode state={state}>
    {(value: string) => <ChallengeQRCodeComponent value={value} onClick={() => undefined} />}
  </StatedChallengeQRCode>
);
