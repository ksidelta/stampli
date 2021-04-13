import React from 'react';

export const Condition = ({ predicate, children }: { children: React.ReactNode; predicate: boolean }) => {
  return <>{predicate ? children : null}</>;
};
