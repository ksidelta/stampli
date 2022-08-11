import QRCode from 'qrcode.react';
import React from 'react';

export const ChallengeQRCodeComponent = ({ value, onClick }: { value: string; onClick: () => void }) => (
  <div onClick={onClick}>
    <QRCode style={{ width: '30rem', height: '30rem' }} size={512} value={value} />
  </div>
);
