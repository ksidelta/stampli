import { InputState } from '../../../state/form/input/InputState';
import { StatedChallengeQRCode } from './StatedChallengeQRCode';
import { ChallengeQRCodeComponent } from './ChallengeQRCodeComponent';
import React from 'react';

export const ChallengeQRCode = ({ state, onClick }: { state: InputState<string>; onClick: () => void }) => (
  <StatedChallengeQRCode state={state}>
    {(value: string) => <ChallengeQRCodeComponent value={value} onClick={onClick} />}
  </StatedChallengeQRCode>
);
