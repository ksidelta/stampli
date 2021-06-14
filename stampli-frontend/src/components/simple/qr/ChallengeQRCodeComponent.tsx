import QRCode from 'qrcode.react';
import React from 'react';

export const ChallengeQRCodeComponent = ({ value, onClick }: { value: string; onClick: () => void }) => (
  <div onClick={onClick}>
    <QRCode value={value} />
  </div>
);
