export function givwhenify<T>(title: string, before: () => Promise<T>, then: (ret: () => T) => void) {
  describe(title, () => {
    let something: T | undefined = undefined;
    beforeEach(async () => {
      something = await before();
    });

    then(() => something as T);
  });
}
