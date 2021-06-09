import QRCode from 'qrcode.react';
import React from 'react';

export const ChallengeQRCodeComponent = ({ value }: { value: string; onClick: () => void }) => (
  <a href={value}>
    <QRCode value={value} />
  </a>
);
