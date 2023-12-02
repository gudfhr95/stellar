import * as pulumi from '@pulumi/pulumi';
import {beforeAll, beforeEach, describe, expect, it} from '@jest/globals';
import {promiseOf} from './util';

describe('ECR', () => {
  let infra: typeof import('../src/ecr');

  beforeAll(() => {
    pulumi.runtime.setAllConfig({
      'project:profile': 'test',
    });

    pulumi.runtime.setMocks({
      newResource: (
        args: pulumi.runtime.MockResourceArgs
      ): {id: string; state: any} => {
        return {
          id: `${args.name}-id`,
          state: {
            url: `${args.name}-url`,
            repository: {
              name: `${args.inputs.name}`,
            },
          },
        };
      },

      call: (args: pulumi.runtime.MockCallArgs) => {
        return args.inputs;
      },
    });
  });

  beforeEach(async () => {
    infra = await import('../src/ecr');
  });

  describe('backend repository', () => {
    it('should be created', async () => {
      const backendRepositoryName = await promiseOf(
        infra.backendRepository.repository.name
      );
      const backendRepositoryUrl = await promiseOf(infra.backendRepository.url);

      expect(backendRepositoryName).toBe('backend');
      expect(backendRepositoryUrl).toBe('backend-url');
    });
  });
});
