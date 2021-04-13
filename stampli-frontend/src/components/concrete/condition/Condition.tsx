import React from 'react';

export const Condition = ({ predicate, children }: { children: React.ReactNode; predicate: () => boolean }) => (
  <>{predicate() ? children : null}</>
);
